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

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Message is the way in which client and server communicate
 */
public class Message implements Serializable, IsSerializable {
    private State state;
    private AbstractOperation operation;
    private long editingSessionId; // TODO: not sure esid is necessary here.

    public Message() {
    }
    
    public Message(State state, AbstractOperation operation, long sessionId) {
        this(state, operation);
        this.editingSessionId = sessionId;
    }

    public Message(State state, AbstractOperation operation) {
        this.state = new State(state);
        this.operation = operation;  // todo: clone operation
    }

    public Message(Message m) {
        this(new State(m.getState()), m.getOperation(), m.getEditingSessionId());
    }

    public AbstractOperation getOperation() {
        return operation;
    }

    public void setOperation(AbstractOperation operation) {
        this.operation = operation;
    }

    public State getState() {
        return state;
    }

    public int getSiteId() {
        return operation.getSiteId();
    }

    public void setSiteId(int siteId) {
        operation.setSiteId(siteId);
    }

    public long getEditingSessionId() {
        return editingSessionId;
    }

    public void setEditingSessionId(long editingSessionId) {
        this.editingSessionId = editingSessionId;
    }

    @Override
    public String toString() {
        return "Message: " + state + ", " + operation + ", editingSessionId:" + editingSessionId;
    }
}
