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
package fr.loria.score.client;

import fr.loria.score.jupiter.model.Document;
import fr.loria.score.jupiter.plain.PlainDocument;

import java.io.Serializable;

/**
 * DTO class for ClientJupiterAlg
 * @author Bogdan.Flueras@inria.fr
 */
public class ClientDTO implements Serializable {
    private int siteId;
    private int esid;
    private Document document;

    public ClientDTO() {
    }

    public ClientDTO(Document document, int siteId, int esid) {
        this.siteId = siteId;
        this.esid = esid;
        this.document = document;
    }

    public ClientDTO(String content, int siteId, int esid) {
        this(new PlainDocument(content), siteId, esid);
    }

    public ClientDTO(ClientJupiterAlg cja) {
        this.siteId = cja.getSiteId();
        this.esid = cja.getEditingSessionId();
        this.document = cja.getDocument();
    }

    public int getSiteId() {
        return siteId;
    }

    public ClientDTO setSiteId(int siteId) {
        this.siteId = siteId;
        return this;
    }

    public int getEditingSessionId() {
        return esid;
    }

    public ClientDTO setEditingSessionId(int esid) {
        this.esid = esid;
        return this;
    }

    public Document getDocument() {
        return document;
    }

    public ClientDTO setDocument(Document data) {
        this.document = data;
        return this;
    }

    @Override
    public String toString() {
        return "ClientDTO: siteId: " + siteId + ", esid: " + esid + ", document: " + document;
    }
}
