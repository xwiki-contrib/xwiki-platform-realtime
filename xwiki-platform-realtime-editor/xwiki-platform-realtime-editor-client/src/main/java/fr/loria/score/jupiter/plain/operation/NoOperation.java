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

/**
 * No op
 */
public class NoOperation extends Operation {

    public NoOperation(){}

    public NoOperation(int siteId, int position) {
        super(siteId, position);
    }

    @Override
    public void beforeUpdateUI(Editor editor) {}

    @Override
    public void afterUpdateUI(Editor editor) {}

    @Override
    public String toString() {
        return "NoOperation()" + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof NoOperation;
    }
}
