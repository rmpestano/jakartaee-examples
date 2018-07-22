/*
 * Permission to use, copy, modify, and/or distribute this software for any 
 * purpose with or without fee is hereby granted.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR(S) DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR(S) BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION
 * OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN
 * CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */
package jakartaee.examples.websocket.onclose;

import java.io.IOException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * An @OnClose example.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@ServerEndpoint("/echo")
public class OnCloseEndpoint {

    /**
     * Handle the onClose.
     */
    @OnClose
    public void onClose() {
        System.out.println("And now it is time for some cleanup");
    }

    /**
     * Handle the text message.
     *
     * @param session the session.
     * @param message the message.
     */
    @OnMessage
    public void onMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
            session.close();
        } catch (IOException ioe) {
        }
    }
}
