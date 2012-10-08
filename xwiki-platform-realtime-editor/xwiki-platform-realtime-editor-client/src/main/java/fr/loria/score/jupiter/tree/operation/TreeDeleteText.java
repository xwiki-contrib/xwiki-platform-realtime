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

import fr.loria.score.jupiter.tree.Tree;
import fr.loria.score.jupiter.tree.TreeUtils;

public class TreeDeleteText extends TreeOperation {

    public TreeDeleteText() {}

    /**
     * @param siteId is used only by the backend Jupiter algo and not by the transformation functions
     * @param position
     * @param path
     */
    public TreeDeleteText(int siteId, int position, int[] path) {
        super(siteId, position);
        setPath(path);
    }

    public TreeDeleteText(int a, int[] path) {
        super(a);
        setPath(path);
    }

    public void execute(Tree root) {
        Tree tree = root.getChildFromPath(path);
        tree.deleteChar(position);
    }

    public String toString() {
        return "DeleteText(" + super.toString() + ")";
    }

    //OT pour DeleteText
    protected TreeOperation handleTreeInsertText(TreeInsertText op1) {
        if (TreeUtils.diff(op1.path, path)) {
            return op1;
        }
        if (op1.getPosition() <= position) {
            return op1;
        }
        return new TreeInsertText(op1.getSiteId(), op1.getPosition() - 1, op1.path, op1.getText());
    }

    protected TreeOperation handleTreeDeleteText(TreeDeleteText op1) {
        if (TreeUtils.diff(op1.path, path)) {
            return op1;
        }
        if (op1.getPosition() == position) {
            return new TreeIdOp();
        }
        if (op1.getPosition() < position) {
            return op1;
        }
        return new TreeDeleteText(this.siteId, op1.getPosition() - 1, op1.path);
    }

    protected TreeOperation handleTreeNewParagraph(TreeNewParagraph op1) {
        return op1;
    }

    protected TreeOperation handleTreeMergeParagraph(TreeMergeParagraph op1) {
        return op1;
    }

    protected TreeOperation handleTreeInsertParagraph(TreeInsertParagraph op1) {
        if (TreeUtils.diff(op1.path, path)) {
            return op1;
        }
        if (op1.getPosition() <= position) {
            return op1;
        }
        return new TreeInsertParagraph(op1.getSiteId(), op1.getPosition() - 1, op1.path, op1.splitLeft);
    }

    protected TreeOperation handleTreeDeleteTree(TreeDeleteTree op1) {
        return op1;
    }

    protected TreeOperation handleTreeStyle(TreeStyle op1) {
        if (TreeUtils.diff(op1.path, path)) {
            return op1;
        }
        if (op1.start > position) {
            return new TreeStyle(op1.getSiteId(), op1.path, op1.start - 1, op1.end - 1, op1.param, op1.value, op1.addStyle, op1.splitLeft, op1.splitRight,op1.getTagName());
        }
        if (op1.end <= position) {
            return op1;
        }
        return new TreeStyle(op1.getSiteId(), op1.path, op1.start, op1.end - 1, op1.param, op1.value, op1.addStyle, op1.splitLeft, op1.splitRight,op1.getTagName());
    }

    protected TreeOperation handleTreeMoveParagraph(TreeMoveParagraph op1) {
        return op1;
    }

    @Override
    protected TreeOperation handleTreeCaretPosition(TreeCaretPosition op1) {
         if (TreeUtils.diff(op1.path, path)) {
            return op1;
        }
        if (op1.getPosition() <= position) {
            return op1;
        }
        return new TreeCaretPosition(op1.getSiteId(), op1.getPosition() - 1, op1.path);
    }

    @Override
    protected TreeOperation handleTreeMergeItem(TreeMergeItem op1) {
        return op1;
    }

    @Override
    protected TreeOperation handleTreeMoveItem(TreeMoveItem op1) {
        return op1;
    }

    @Override
    protected TreeOperation handleTreeNewItem(TreeNewItem op1) {
        return op1;
    }

    @Override
    protected TreeOperation handleTreeNewList(TreeNewList op1) {
        return op1;
    }

    @Override
    protected TreeOperation handleTreeSplitItem(TreeSplitItem op1) {
        if (TreeUtils.diff(op1.path, path)) {
            return op1;
        }
        if (op1.getPosition() <= position) {
            return op1;
        }
        return new TreeSplitItem(op1.getSiteId(), op1.getPosition()-1, op1.path, op1.splitLeft);
    }

    @Override
    protected TreeOperation handleTreeUpdateElement(TreeUpdateElement op1) {
        return op1;
    }
}
