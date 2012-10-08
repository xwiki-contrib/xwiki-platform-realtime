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
package fr.loria.score.jupiter.tree;

import fr.loria.score.jupiter.model.AbstractOperation;
import fr.loria.score.jupiter.model.Document;
import fr.loria.score.jupiter.tree.operation.TreeOperation;

import java.util.logging.Logger;

/**
 * Wrapper over a hierarchical tree model
 * @author Bogdan.Flueras@inria.fr
 */
public class TreeDocument implements Document {
    private transient static final Logger log = Logger.getLogger(TreeDocument.class.getName());
    private Tree root;

    public TreeDocument() {
    }

    public TreeDocument(Tree root) {
        this.root = root;
    }

    public Tree getRoot() {
        return root;
    }

    @Override
    public String getContent() {
        return root.toString();
    }

    @Override
    public void setContent(String content) {
        //nothing
    }

    @Override
    public void apply(AbstractOperation op) {
        log.fine("Applying: " + op + " to document: " + root.toString());
        if (op instanceof TreeOperation) {
            TreeOperation treeOperation = (TreeOperation) op;
            treeOperation.execute(root);
        }
    }

    @Override
    public Document deepCloneDocument() {
        Tree clonedTree = TreeUtils.cloneTree(root);
        return new TreeDocument(clonedTree);
    }

    @Override
    public String toString() {
        return getContent();
    }
}
