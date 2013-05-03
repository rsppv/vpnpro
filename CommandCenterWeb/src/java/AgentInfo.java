/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import compute.Compute;

/**
 *
 * @author aipova
 */
public class AgentInfo {

    private Integer id;
    private Compute stub;
    private String ip;
    // еще чтото

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Compute getStub() {
        return stub;
    }

    public void setStub(Compute stub) {
        this.stub = stub;
    }

    public AgentInfo() {
    }

    public AgentInfo(Integer id, Compute stub, String ip) {
        this.id = id;
        this.stub = stub;
        this.ip = ip;
    }
}
