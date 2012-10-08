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

import org.junit.Test;

import fr.loria.score.jupiter.tree.operation.TreeNewParagraph;
import fr.loria.score.jupiter.tree.operation.TreeOperation;

import static fr.loria.score.TreeDSL.paragraph;
import static fr.loria.score.TreeDSL.hr;
import static fr.loria.score.TreeDSL.text;
import fr.loria.score.jupiter.tree.operation.TreeMergeParagraph;
import static org.junit.Assert.assertEquals;

/**
 * Tests TreeMergeParagraph operation in different cases
 *
 * @author Gerald.Oster@loria.fr
 * 
 */
public class TreeMergeParagraphTest extends AbstractTreeOperationTest
{
    @Test
    public void mergeHRwithParagraph()
    {
        rootDSL.addChild(paragraph().addChild(text("ab")),
                hr(),
                paragraph().addChild(text("cd")));

        // Split an horizontal rule with the next paragraph        
        final TreeMergeParagraph mergeHRwithParagraph = new TreeMergeParagraph(SITE_ID, 2, 0, 1);
        mergeHRwithParagraph.execute(rootDSL.getTree());
        
        // expectedRoot = <p>[ab]</p><p>[cd]</p>
        expectedRootDSL.addChild(paragraph().addChild(text("ab")),
                                 paragraph().addChild(text("cd")));

        assertEquals("Invalid tree ", expectedRootDSL.getTree(), rootDSL.getTree());
    }
    
    @Test
    public void mergeHRwithParagraph2()
    {
        rootDSL.addChild(paragraph().addChild(text("ab")),
                hr().addChild((text(""))),
                paragraph().addChild(text("cd")));

        // Split an horizontal rule with the next paragraph        
        final TreeMergeParagraph mergeHRwithParagraph = new TreeMergeParagraph(SITE_ID, 2, 1, 1);
        mergeHRwithParagraph.execute(rootDSL.getTree());
        
        // expectedRoot = <p>[ab]</p><p>[][cd]</p>
        expectedRootDSL.addChild(paragraph().addChild(text("ab")),
                                 paragraph().addChild(text(""), text("cd")));

        assertEquals("Invalid tree ", expectedRootDSL.getTree(), rootDSL.getTree());
    }
} 