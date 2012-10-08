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
package fr.loria.score.jupiter.model;


import java.io.Serializable;

/**
 * Represents a document on which OT algorithms work: a plain string, a tree etc.<br/>
 * On different algorithms, the document may contain more information than the visible text such as metadata
 * (tombstone invisible chars etc).
 *
 * @author sebastien.parisot
 * @author Bogdan.Flueras@inria.fr
 */
public interface Document extends Serializable {
    /**
     * @return the view of the document, what it is expected to be viewable by user according to the used algorithm
     */
    public String getContent();

    /**
     * @param content the content to be set for this document
     */
    public void setContent(String content);

    /**
     * Apply an operation to the document and modifies the content of the document according to the semantics of the operation
     *
     * @param op the operation to be applied on this document
     */
    public void apply(AbstractOperation op);

    /**
     * @return a deep clone of this Document
     */
    public Document deepCloneDocument();
}
