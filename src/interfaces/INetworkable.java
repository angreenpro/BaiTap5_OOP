package interfaces;

public interface INetworkable {

    String getIpAddress();

    boolean isConnected();

    void connect(String ipAddress);

    void disconnect();
}
