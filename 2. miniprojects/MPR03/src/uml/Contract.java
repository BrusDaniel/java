package uml;

import java.time.LocalDate;

public class Contract extends ObjectPlusPlus {

    private final int id;
    private final Manager manager;
    private final ExternalAgent externalAgent;
    private final String content;
    private final LocalDate effectiveStartDate;
    private final LocalDate completionDeadline;
    private static int nextId = 1;

    public Contract(Manager manager, ExternalAgent externalAgent, String content,
                    LocalDate effectiveStartDate, LocalDate completionDeadline) {
        super();

        if (manager == null) throw new IllegalArgumentException("Manager is required!");
        if (externalAgent == null) throw new IllegalArgumentException("External agent is required!");
        if (content == null) throw new IllegalArgumentException("Content is required!");
        if (effectiveStartDate == null) throw new IllegalArgumentException("Effective start date is required!");
        if (completionDeadline == null) throw new IllegalArgumentException("Completion deadline is required!");
        if (completionDeadline.isBefore(effectiveStartDate)) throw new IllegalArgumentException("Completion deadline cannot be before effective start date!");

        this.id = nextId++;
        this.manager = manager;
        this.externalAgent = externalAgent;
        this.content = content;
        this.effectiveStartDate = effectiveStartDate;
        this.completionDeadline = completionDeadline;

        manager.addContract(this);
        externalAgent.addContract(this);
    }

    public int getId() {return id;}
    public Manager getManager() {return manager;}
    public ExternalAgent getExternalAgent() {return externalAgent;}
    public String getContent() {return content;}
    public LocalDate getEffectiveStartDate() {return effectiveStartDate;}
    public LocalDate getCompletionDeadline() {return completionDeadline;}

    @Override
    public String toString() {
        return "[CONTRACT] id=" + id +
                ", manager=" + manager.getFirstName() + " " + manager.getSurname() +
                ", externalAgent=" + externalAgent.getTitle() +
                ", content='" + content + '\'' +
                ", effectiveStartDate=" + effectiveStartDate +
                ", completionDeadline=" + completionDeadline;
    }
}