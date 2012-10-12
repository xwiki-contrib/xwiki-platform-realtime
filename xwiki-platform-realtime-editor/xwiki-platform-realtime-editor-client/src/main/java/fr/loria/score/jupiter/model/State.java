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
 * Models the current state of the client or the server. This is an indicator of how many messages were locally generated and how many were received.
 * Basically it is a vector clock of two entries.
 * <ul>
 * <li>generatedMsgs - the nr of generated messages by the owner entity - could be client or server</li>
 * <li>receivedMsgs - the nr of received and processed messages by the owner entity - could be client or server.</li>
 * </ul>
 */
public class State implements Serializable, IsSerializable {
    private int generatedMsgs = 0;
    private int receivedMsgs = 0;

    public State() {
    }

    public State(State s) {
        this(s.generatedMsgs, s.receivedMsgs);
    }

    public State(int myMsgs, int receivedMsgs) {
        this.generatedMsgs = myMsgs;
        this.receivedMsgs = receivedMsgs;
    }

    public int getGeneratedMsgs() {
        return generatedMsgs;
    }

    public void incGeneratedMsgs() {
        this.generatedMsgs++;
    }

    public int getReceivedMsgs() {
        return receivedMsgs;
    }

    public void incReceivedMsgs() {
        this.receivedMsgs++;
    }

    @Override
    public String toString() {
        return "State(" + generatedMsgs + ", " + receivedMsgs + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof State)){
            return false;
        }
        State other = (State)o;
        return other.generatedMsgs == this.generatedMsgs && other.receivedMsgs == this.receivedMsgs;
    }

    @Override
    public int hashCode() {
        return this.generatedMsgs * 19 + this.receivedMsgs * 13;
    }
}
