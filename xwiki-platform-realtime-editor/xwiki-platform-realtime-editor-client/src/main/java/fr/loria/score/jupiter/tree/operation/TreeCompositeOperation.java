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
package fr.loria.score.jupiter.tree.operation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.loria.score.jupiter.tree.Tree;

public class TreeCompositeOperation extends TreeOperation {

    private ArrayList<TreeOperation> operations = new ArrayList<TreeOperation>();

    public TreeCompositeOperation() {}

    public TreeCompositeOperation(ArrayList<TreeOperation> operations) {
        this.operations = operations;
    }

    public TreeCompositeOperation(TreeOperation... ops) {
        for (TreeOperation tOp : ops) {
            operations.add(tOp);
        }
    }

    public TreeCompositeOperation add(TreeOperation op) {
        operations.add(op);
        return this;
    }

    public void execute(Tree root) {
        Iterator<TreeOperation> it = operations.iterator();
        while (it.hasNext()) {
            it.next().execute(root);
        }
    }

    public String toString() {
        Iterator<TreeOperation> it = operations.iterator();
        String s = it.next().toString();
        while (it.hasNext()) {
            s = s + ", " + it.next().toString();
        }
        return "Composite(" + s + ")";
    }

    public List<TreeOperation> getOperations() {
        return new ArrayList<TreeOperation>(operations);
    }

    public TreeOperation transform(TreeOperation op1) {
        Iterator<TreeOperation> it = operations.iterator();
        TreeOperation to = op1;
        while (it.hasNext()) {
            to = (TreeOperation) it.next().transform(to);
        }
        return to;
    }

    @Override
    public void setSiteId(int siteId) {
        for (TreeOperation op : operations) {
            op.setSiteId(siteId);
        }
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
