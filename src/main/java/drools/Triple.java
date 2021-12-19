package drools;

public class Triple {
    private String Head_entity;
    private String relation;
    private String Tail_entity;

    public Triple(String Head_entity, String relation, String Tail_entity) {
        this.Head_entity = Head_entity;
        this.relation = relation;
        this.Tail_entity = Tail_entity;

    }

    public String getHead_entity() {
        return Head_entity;
    }

    public void setHead_entity(String Head_entity) {
        this.Head_entity = Head_entity;
    }

    public String getrelation() {
        return relation;
    }

    public void setrelation(String relation) {
        this.relation = relation;
    }

    public String getTail_entity() {
        return Tail_entity;
    }

    public void setTail_entity(String Tail_entity) {
        this.Tail_entity = Tail_entity;
    }

    @Override
    public String toString() {
        return Head_entity + " " + relation + " " + Tail_entity + " .";
    }
}
