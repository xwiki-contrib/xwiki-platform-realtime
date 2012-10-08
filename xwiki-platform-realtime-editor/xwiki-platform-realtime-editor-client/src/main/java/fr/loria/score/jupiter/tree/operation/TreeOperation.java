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
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Logger;

import fr.loria.score.jupiter.model.AbstractOperation;
import fr.loria.score.jupiter.tree.Tree;

public abstract class TreeOperation extends AbstractOperation {
    private transient static final Logger log = Logger.getLogger(TreeOperation.class.getName());

    public TreeOperation(AbstractOperation o) {
        super(o);
    }

    public TreeOperation(int siteId, int position) {
        super(siteId, position);
    }

    public TreeOperation(int position) {
        super(position);
    }

    public TreeOperation() {}

    public TreeOperation(int[] path) {
        this.path = path;
    }

    protected int[] path; //path node

    public abstract void execute(Tree root);

    public AbstractOperation transform(AbstractOperation op1) {
        log.finest("Transforming:" + op1 + " with respect to this operation: " + this);
        TreeOperation transformed = null;
        if (op1 instanceof TreeInsertText) {
            transformed = handleTreeInsertText((TreeInsertText) op1);
        } else if (op1 instanceof TreeDeleteText) {
            transformed = handleTreeDeleteText((TreeDeleteText) op1);
        } else if (op1 instanceof TreeNewParagraph) {
            transformed = handleTreeNewParagraph((TreeNewParagraph) op1);
        } else if (op1 instanceof TreeMergeParagraph) {
            transformed = handleTreeMergeParagraph((TreeMergeParagraph) op1);
        } else if (op1 instanceof TreeInsertParagraph) {
            transformed = handleTreeInsertParagraph((TreeInsertParagraph) op1);
        } else if (op1 instanceof TreeDeleteTree) {
            transformed = handleTreeDeleteTree((TreeDeleteTree) op1);
        } else if (op1 instanceof TreeStyle) {
            transformed = handleTreeStyle((TreeStyle) op1);
        } else if (op1 instanceof TreeMoveParagraph) {
            transformed = handleTreeMoveParagraph((TreeMoveParagraph) op1);
        } else if (op1 instanceof TreeIdOp) { // todo: this goes for all operations
            transformed = handleTreeId((TreeIdOp) op1);
        } else if (op1 instanceof TreeCompositeOperation) {
            transformed = handleTreeComposite((TreeCompositeOperation) op1);
        } else if(op1 instanceof TreeCaretPosition){
            transformed = handleTreeCaretPosition((TreeCaretPosition) op1);
        } else if(op1 instanceof TreeMergeItem){
            transformed = handleTreeMergeItem((TreeMergeItem) op1);
        } else if(op1 instanceof TreeMoveItem){
            transformed = handleTreeMoveItem((TreeMoveItem) op1);
        } else if(op1 instanceof TreeNewItem){
            transformed = handleTreeNewItem((TreeNewItem) op1);
        } else if(op1 instanceof TreeNewList){
            transformed = handleTreeNewList((TreeNewList) op1);
        } else if(op1 instanceof TreeSplitItem){
            transformed = handleTreeSplitItem((TreeSplitItem) op1);
        } else if(op1 instanceof TreeUpdateElement){
            transformed = handleTreeUpdateElement((TreeUpdateElement) op1);
        }   
        return transformed;
    }

    // make an intf  & delegate
    protected abstract TreeOperation handleTreeInsertText(TreeInsertText op1);

    protected abstract TreeOperation handleTreeDeleteText(TreeDeleteText op1);

    protected abstract TreeOperation handleTreeNewParagraph(TreeNewParagraph op1);

    protected abstract TreeOperation handleTreeMergeParagraph(TreeMergeParagraph op1);

    protected abstract TreeOperation handleTreeInsertParagraph(TreeInsertParagraph op1);

    protected abstract TreeOperation handleTreeDeleteTree(TreeDeleteTree op1);

    protected abstract TreeOperation handleTreeStyle(TreeStyle op1);

    protected abstract TreeOperation handleTreeMoveParagraph(TreeMoveParagraph op1);
    
    protected abstract TreeOperation handleTreeMergeItem(TreeMergeItem op1);
    
    protected abstract TreeOperation handleTreeMoveItem(TreeMoveItem op1);
    
    protected abstract TreeOperation handleTreeNewItem(TreeNewItem op1);
    
    protected abstract TreeOperation handleTreeNewList(TreeNewList op1);
    
    protected abstract TreeOperation handleTreeSplitItem(TreeSplitItem op1);
    
    protected abstract TreeOperation handleTreeUpdateElement(TreeUpdateElement op1);
    
    protected abstract TreeOperation handleTreeCaretPosition(TreeCaretPosition op1);

    protected TreeOperation handleTreeId(TreeIdOp op1) {
        return op1;
    }

    protected TreeOperation handleTreeComposite(TreeCompositeOperation op1) {
        ArrayList<TreeOperation> l = new ArrayList<TreeOperation>();
        Iterator<TreeOperation> it = op1.getOperations().iterator();

        TreeOperation to = this;
        TreeOperation n = it.next();

        l.add((TreeOperation)to.transform(n));

        while (it.hasNext()) {
            to = (TreeOperation)n.transform(to);
            n = it.next();
            l.add((TreeOperation) to.transform(n));
        }
        return new TreeCompositeOperation(l);
    }

    public String toString() {
        return "siteId: " + siteId + ", position: " + position + ", path: " + Arrays.toString(path);
    }

    public int[] getPath() {
        return path;
    }

    public void setPath(int[] path) {
        this.path = path;
    }
}
