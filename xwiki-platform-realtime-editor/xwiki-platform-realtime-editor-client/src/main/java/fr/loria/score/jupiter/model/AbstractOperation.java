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
 * Represents a generic operation to be applied to a Document.
 * @author Bogdan.Flueras@inria.fr
 */

public abstract class AbstractOperation implements Serializable {
    public static transient final String THE_POSITION_IS_NEGATIVE = "The position is negative: ";

    /**
     * The ID of the site which created this operation
     */
    protected int siteId;

    /**
     * The position (0 based) at which the operation should be applied on the  Document model content
     */
    protected int position;

    public AbstractOperation() {}

    public AbstractOperation(int siteId, int position) {
        this(position);
        this.siteId = siteId;
    }

    public AbstractOperation(AbstractOperation o) {
        this(o.getSiteId(), o.getPosition());
    }

    public AbstractOperation(int position) {
        if (position < 0) {
            throw new IllegalArgumentException(THE_POSITION_IS_NEGATIVE + position);
        }
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    /**
     * Transforms this operation with respect to the given operation
     * @param op1 an operation
     * @return the transformed operation
     */
    public abstract AbstractOperation transform(AbstractOperation op1);
}