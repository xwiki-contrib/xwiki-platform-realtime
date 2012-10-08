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


import java.util.logging.Logger;

import fr.loria.score.jupiter.model.AbstractOperation;

/**
 * Transformation for concurrent operations on a tree like model
 * @author Bogdan.Flueras@inria.fr
 */
public class TreeTransformation extends Transformation {
    private static final Logger log = Logger.getLogger(TreeTransformation.class.getName());

    @Override
    public AbstractOperation transform(AbstractOperation op1, AbstractOperation op2) {
        log.finest("Transforming op1:" + op1 +" with respect to op2: " + op2);
        AbstractOperation result = op2.transform(op1);
        return result;
    }
}
