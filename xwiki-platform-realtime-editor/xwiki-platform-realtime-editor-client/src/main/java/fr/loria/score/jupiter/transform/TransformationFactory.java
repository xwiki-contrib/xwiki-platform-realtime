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
package fr.loria.score.jupiter.transform;

import fr.loria.score.jupiter.model.Document;
import fr.loria.score.jupiter.plain.PlainDocument;
import fr.loria.score.jupiter.tree.TreeDocument;

/**
 * Abstract Factory for transformations creation
 */

public class TransformationFactory {
    public static Transformation createResselTransformation() {
        return new ResselTransformation();
    }

    public static Transformation createTreeTransformation() {
        return new TreeTransformation();
    }

    /**
     * @param doc the document upon to act
     * @return a transformation based on the document type
     */
    public static Transformation createTransformation(Document doc) {
        if (doc instanceof PlainDocument) {
            return createResselTransformation();
        } else if (doc instanceof TreeDocument) {
            return createTreeTransformation();
        }
        return null;
    }
}