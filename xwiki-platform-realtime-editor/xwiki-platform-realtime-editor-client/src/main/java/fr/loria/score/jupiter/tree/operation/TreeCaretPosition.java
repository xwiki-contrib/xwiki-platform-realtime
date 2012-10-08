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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.loria.score.jupiter.tree.operation;

import fr.loria.score.jupiter.tree.Tree;

/**
 *
 * @author andre
 */
public class TreeCaretPosition extends TreeOperation{

    public TreeCaretPosition()
    {
    }

    public TreeCaretPosition(int siteId, int position, int[] path) {
        this.siteId=siteId;
        this.position=position;
        this.path=path;
    }

    @Override
    public void execute(Tree root)
    {
    }

    @Override
    protected TreeOperation handleTreeInsertText(TreeInsertText op1) {
        return null;
    }

    @Override
    protected TreeOperation handleTreeDeleteText(TreeDeleteText op1) {
        return null;
    }

    @Override
    protected TreeOperation handleTreeNewParagraph(TreeNewParagraph op1) {
        return null;
    }

    @Override
    protected TreeOperation handleTreeMergeParagraph(TreeMergeParagraph op1) {
        return null;
    }

    @Override
    protected TreeOperation handleTreeInsertParagraph(TreeInsertParagraph op1) {
        return null;
    }

    @Override
    protected TreeOperation handleTreeDeleteTree(TreeDeleteTree op1) {
        return null;
    }

    @Override
    protected TreeOperation handleTreeStyle(TreeStyle op1) {
        return null;
    }

    @Override
    protected TreeOperation handleTreeMoveParagraph(TreeMoveParagraph op1) {
        return null;
    }

    @Override
    protected TreeOperation handleTreeCaretPosition(TreeCaretPosition op1) {
        return null;
    }

    @Override public String toString()
    {
        return "TreeCaretPos(" + super.toString() + ")";
    }

    @Override
    protected TreeOperation handleTreeMergeItem(TreeMergeItem op1) {
        return null;
    }

    @Override
    protected TreeOperation handleTreeMoveItem(TreeMoveItem op1) {
        return null;
    }

    @Override
    protected TreeOperation handleTreeNewItem(TreeNewItem op1) {
        return null;
    }

    @Override
    protected TreeOperation handleTreeNewList(TreeNewList op1) {
        return null;
    }

    @Override
    protected TreeOperation handleTreeSplitItem(TreeSplitItem op1) {
        return null;
    }

    @Override
    protected TreeOperation handleTreeUpdateElement(TreeUpdateElement op1) {
        return null;
    }
}
