package com.trustinno.win.jobagtrustinno.Server;

/**
 * Created by zarni on 1/25/17.
 */

public class ServerEvent {
    private ServerResponse serverResponse;

    public ServerEvent(ServerResponse serverResponse) {

        this.serverResponse = serverResponse;

    }

    public ServerResponse getServerResponse() {
        return serverResponse;
    }

    public void setServerResponse(ServerResponse serverResponse) {
        this.serverResponse = serverResponse;
    }

}
