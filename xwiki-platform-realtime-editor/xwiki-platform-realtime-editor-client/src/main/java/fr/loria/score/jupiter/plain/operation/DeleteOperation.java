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
package fr.loria.score.jupiter.plain.operation;

import fr.loria.score.client.Editor;

import java.util.logging.Logger;

/**
 * Deletes a character at the given position
 */
public class DeleteOperation extends Operation {
    private transient static final Logger logger = Logger.getLogger(DeleteOperation.class.getName());

    public DeleteOperation() {}

    public DeleteOperation(int siteId, int startPos) {
        super(siteId, startPos);
    }

    @Override
    public void beforeUpdateUI(Editor editor) {
        logger.info("Highlighting: Removing: " + siteId + " at position: " + position);
        editor.prepareUI(position, siteId, true);
    }

    @Override
    public void afterUpdateUI(Editor editor) {
        logger.info("Update UI for Delete operation...");
        if (position < editor.getCaretPosition()) {
            editor.shiftCaret(editor.getOldCaretPos() - 1);
        }
    }

    @Override
    public String toString() {
        return "DeleteOperation(" + position + ")" + super.toString();
    }
    //todo: are they used?
    public Operation handleInsert(InsertOperation op1) {
        return null;
    }

    public Operation handleDelete(DeleteOperation op1) {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DeleteOperation) {
            DeleteOperation other = (DeleteOperation) obj;
            return this.siteId == other.siteId && this.position == other.position;
        }
        return false;
    }
}
