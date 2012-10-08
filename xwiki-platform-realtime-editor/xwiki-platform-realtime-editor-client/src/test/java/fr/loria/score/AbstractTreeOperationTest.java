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
package fr.loria.score;

import org.junit.Before;

import fr.loria.score.jupiter.tree.TreeFactory;

/**
 * Test the effect of executing tree operations on the tree model. It should test all Tree API
 *
 * @author Bogdan.Flueras@inria.fr
 * @author Gerald.Oster@loria.fr
 */
public class AbstractTreeOperationTest
{
    // TODO: naming convention
    protected TreeDSL rootDSL;

    protected TreeDSL expectedRootDSL;

    protected static final boolean SPLIT_LEFT = true;

    protected static final boolean NO_SPLIT_LEFT = false;

    protected static final boolean SPLIT_RIGHT = true;

    protected static final boolean NO_SPLIT_RIGHT = false;

    protected static final boolean ADD_STYLE = true;

    protected static final boolean NO_ADD_STYLE = false;

    protected static final int SITE_ID = 0;

    @Before
    public void init()
    {
        rootDSL = new TreeDSL(TreeFactory.createEmptyTree());
        expectedRootDSL = new TreeDSL(TreeFactory.createEmptyTree());
    }

    public static int[] path(int... positions)
    {
        int[] path = positions;
        return path;
    }
}
