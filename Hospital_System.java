import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class CaseComparator<String> implements Comparator<String> {

    public int casemeasure(String x) {
        if (x.equals("minor"))
            return 1;
        else if (x.equals("moderate"))
            return 2;
        else if (x.equals("major"))
            return 3;
        else if (x.equals("extreme"))
            return 4;
        else
            return 0;
    }

    public int compare(String c1, String c2) throws ClassCastException {
        return casemeasure(c1) - casemeasure(c2);
    }
}

class PatientNode {

    public String name;
    public String Case;
    public PatientNode next;

    public PatientNode(String name, String case1) {
        this.name = name;
        Case = case1;
    }
}

class PriorityQ {

    public PatientNode head;
    public int size;

    public CaseComparator<String> cc = new CaseComparator();

    public ArrayList<String> names = new ArrayList<>();

    public PriorityQ() {
        head = null;
        size = 0;
    }

    public void insert(PatientNode x, PatientNode new_patient) {
        if (x == null) {
            head = new_patient;
            size++;
            names.add(new_patient.name);
        } else if (x.next == null && cc.compare(x.Case, new_patient.Case) >= 0) {
            x.next = new_patient;
            size++;
            names.add(new_patient.name);
        } else if (x.next == null || cc.compare(x.Case, new_patient.Case) < 0) {
            PatientNode cu = head;
            head = new_patient;
            head.next = cu;
            size++;
            names.add(new_patient.name);
        } else if (cc.compare(x.next.Case, new_patient.Case) >= 0)
            insert(x.next, new_patient);
        else {
            new_patient.next = x.next;
            x.next = new_patient;
            size++;
            names.add(new_patient.name);
        }
    }

    public void delete() {
        head = head.next;
    }

    public PatientNode peek() {
        return head;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printQ() {
        PatientNode cur = head;
        System.out.print("[");
        while (cur.next != null) {
            System.out.print("\"" + cur.name + " (" + cur.Case + ")\", ");
            cur = cur.next;
        }
        System.out.print("\"" + cur.name + " (" + cur.Case + ")\"].");
    }

    public void remove(PatientNode x) {
        PatientNode c = head;

        if (head.name.equalsIgnoreCase(x.name))
            delete();

        while (c.next != null) {
            if (c.next.name.equalsIgnoreCase(x.name)) {
                c.next = c.next.next;
                break;
            }
            c = c.next;
        }
    }

    public PatientNode returnmin(PatientNode h) {
        return head;
    }

    public void printnames() {

        Collections.sort(names);
        System.out.println(names.toString());

    }
}

public class Hospital_System {
    public static void main(String[] args) {

        PatientNode h = new PatientNode("Ahmad", "minor");
        PatientNode g = new PatientNode("Sarah", "moderate");
        PatientNode n = new PatientNode("Ali", "minor");
        PatientNode o = new PatientNode("Jalal", "extreme");
        PatientNode v = new PatientNode("Marwan", "major");

        PriorityQ q = new PriorityQ();
        q.insert(null, n);
        q.insert(q.head, h);
        q.insert(q.head, v);
        q.insert(q.head, g);
        q.insert(q.head, o);

        System.out.println();

        q.printQ();
        System.out.println("\n\nThe size is " + q.size + " and isEmpty = " + q.isEmpty());
        System.out.println("The peek is: " + q.peek().name + ", " + q.peek().Case);

        System.out.println("\nNames sorted: ");
        q.printnames();

    }
}