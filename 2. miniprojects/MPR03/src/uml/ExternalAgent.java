package uml;

import java.util.*;

public class ExternalAgent extends ObjectPlusPlus {

    private final int id;
    private final String title;
    private final String NIP;
    private final String email;
    private String address;
    private static int nextId = 1;
    private final List<Contract> contracts = new ArrayList<>();

    private static final Set<String> NIPs = new HashSet<>();

    public ExternalAgent(String title, String NIP, String email, String address) {
        super();

        if (title == null) throw new IllegalArgumentException("Title is required!");
        if (NIP == null) throw new IllegalArgumentException("NIP is required!");
        if (email == null) throw new IllegalArgumentException("Email is required!");
        if (address == null) throw new IllegalArgumentException("Address is required!");
        if (NIPs.contains(NIP)) throw new IllegalArgumentException("NIP must be unique!");

        this.id = nextId++;
        this.title = title;
        this.NIP = NIP;
        this.email = email;
        this.address = address;

        NIPs.add(NIP);
    }

    public int getId() {return id;}
    public String getTitle() {return title;}
    public String getNIP() {return NIP;}
    public String getEmail() {return email;}
    public String getAddress() {return address;}

    void addContract(Contract contract) {
        if (contract == null) throw new IllegalArgumentException("Contract is required!");
        contracts.add(contract);
    }

    public List<Contract> getContracts() {
        return List.copyOf(contracts);
    }

    @Override
    public String toString() {
        return "[EXTERNAL AGENT] id=" + id +
                ", title='" + title + '\'' +
                ", NIP='" + NIP + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'';
    }
}