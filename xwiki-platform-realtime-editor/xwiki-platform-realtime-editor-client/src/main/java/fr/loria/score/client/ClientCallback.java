/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package fr.loria.score.client;

import fr.loria.score.jupiter.model.Document;
import fr.loria.score.jupiter.model.Message;
import fr.loria.score.jupiter.plain.operation.Operation;

import java.util.logging.Logger;

/**
 * Callback which performs different actions: initializing, updating UI aso
 * @author Bogdan.Flueras@inria.fr
 */
public interface ClientCallback {
    static final Logger log = Logger.getLogger(ClientCallback.class.getName());

    /**
     * Executed after the client is connected to server
     * @param dto the DTO replied by the server used to initialize and update different things on client
     * @param document the document
     * @param updateUI if <code>true</code> syncs the view according to the new received document content
     */
    void onConnected(ClientDTO dto, Document document, boolean updateUI);

    /**
     * Executed when client is disconnected from server
     */
    void onDisconnected();

    /**
     * Executed <strong>before</strong> each time the client sends the message to the server
     * @param message the message to be sent
     */
    void beforeSend(Message message);

    /**
     * Executed each time the client receives a new message from the server, <strong>after</strong> the original message was transformed
     * @param receivedMessage the transformed message
     */
    void afterReceive(Message receivedMessage);


    /**
     * Callback for plain text documents, wiki editor
     */
    public class PlainClientCallback implements ClientCallback {
        private Editor editor;
        private Document document;

        public PlainClientCallback(Editor editor) {
            this.editor = editor;
        }

        @Override
        public void onConnected(ClientDTO dto, Document document, boolean updateUI) {
            this.document = document;

            final int siteId = dto.getSiteId();
            editor.setSiteId(siteId);

            if (updateUI) {
                editor.setContent(dto.getDocument().getContent());
                editor.paint();
            }
        }

        @Override
        public void onDisconnected() {}

        @Override
        public void beforeSend(Message message) {}

        @Override
        public void afterReceive(Message receivedMessage) {
            log.fine("Executing received: " + receivedMessage);
            int oldCaretPos = editor.getCaretPosition(); // the caret position in the editor's old document model
            editor.setOldCaretPos(oldCaretPos);

            Operation operation = (Operation)receivedMessage.getOperation();
            operation.beforeUpdateUI(editor); // the highlighter code here

            editor.setContent(document.getContent()); // sets the WHOLE text

            if (editor.getSiteId() != operation.getSiteId()) {
                operation.afterUpdateUI(editor);
            }

            editor.paint(); //cursor.focus() paints it, but it might occur that the page is not focused and thus not updated
        }
    }
}
