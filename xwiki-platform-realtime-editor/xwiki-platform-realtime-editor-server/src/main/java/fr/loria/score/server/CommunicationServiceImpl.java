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
package fr.loria.score.server;

import fr.loria.score.client.ClientDTO;
import fr.loria.score.client.CommunicationService;
import fr.loria.score.jupiter.model.Document;
import fr.loria.score.jupiter.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xwiki.component.annotation.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class CommunicationServiceImpl implements CommunicationService
{

    // the client id generator
    private final AtomicInteger atomicInt = new AtomicInteger();

    /* The logger to use for logging. */
    private Logger logger = LoggerFactory.getLogger(CommunicationServiceImpl.class);

    /**
     * {@inheritDoc}
     */
    public Integer generateClientId()
    {
        int increment = atomicInt.getAndIncrement();
        logger.debug("Generated client id: " + increment);
        return increment;
    }

    /**
     * {@inheritDoc}
     */
    public Message[] clientReceive(int siteId)
    {
        logger.debug("Client receive for siteId: " + siteId);
        return ClientServerCorrespondents.getInstance().clientReceive(siteId);
    }

    /**
     * {@inheritDoc}
     */
    public void serverReceive(Message msg)
    {
        synchronized (this) { //todo: test the old synch
        logger.debug("Server receives message: " + msg);
        ClientServerCorrespondents.getInstance().serverReceive(msg);
        }
    }

    /**
     * {@inheritDoc}
     */
    public Document createServerPairForClient(ClientDTO clientJupiterAlg)
    {
        logger.debug("Create server pair for client with id: " + clientJupiterAlg.getSiteId());
        return ClientServerCorrespondents.getInstance().addServerForClient(clientJupiterAlg);
    }

    public void removeServerPairForClient(ClientDTO clientJupiterAlg)
    {
        logger.debug("Remove server pair for client with id: " + clientJupiterAlg.getSiteId());
        ClientServerCorrespondents.getInstance().removeServerForClient(clientJupiterAlg);
    }

    @Override
    public ClientDTO initClient(ClientDTO client) {
        client.setSiteId(generateClientId());
        client.setDocument(createServerPairForClient(client));
        return client;
    }
}
