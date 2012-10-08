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
package fr.loria.score.jupiter.plain;

import fr.loria.score.jupiter.model.AbstractOperation;
import fr.loria.score.jupiter.model.Document;
import fr.loria.score.jupiter.plain.operation.DeleteOperation;
import fr.loria.score.jupiter.plain.operation.InsertOperation;

import java.util.logging.Logger;

/**
 * Plain text document. A simple wrapper for a String
 * @author Bogdan.Flueras@inria.fr
 */
public class PlainDocument implements Document {
    public transient static final String POSITION_GREATER_THAN_DATA_LENGTH =
            "Position: {0} is greater than document length: {1} ";
    private transient static final Logger logger = Logger.getLogger(PlainDocument.class.getName());

    private String content;

    public PlainDocument() {
    }

    public PlainDocument(String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void apply(AbstractOperation op) {
        logger.fine("\tExecuting " + op + " on content = " + content);

        int length = content.length();
        int position = op.getPosition();
        StringBuilder sb = new StringBuilder(content);

        //won't delegate to operation because the code is almost the same ;)
        if (op instanceof InsertOperation) {
            if (position > length) {
                handleError(position, length);
            }

            sb = new StringBuilder(content);
            sb.insert(position, ((InsertOperation) op).getChr());
        } else if (op instanceof DeleteOperation) {
            if (position >= length) {
               handleError(position, length);
            }
            sb.deleteCharAt(position);
        }
        content = sb.toString();
    }

    @Override
    public Document deepCloneDocument() {
        return new PlainDocument(content);
    }

    @Override
    public String toString() {
        return getContent();
    }

    /**
     * Custom formatter for message error
     */
    private void handleError(int position, int length) {
        String errMsg = POSITION_GREATER_THAN_DATA_LENGTH.
                        replace("{0}", String.valueOf(position)).
                        replace("{1}", String.valueOf(length));
        logger.severe(errMsg);
        throw new IllegalArgumentException(errMsg);
    }
}
