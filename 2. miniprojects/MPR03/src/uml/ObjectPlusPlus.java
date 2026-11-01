package uml;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public abstract class ObjectPlusPlus extends ObjectPlus implements Serializable {

    private Map<String, Map<Object, ObjectPlusPlus>> links = new Hashtable<>();
    private static Set<ObjectPlusPlus> allParts = new HashSet<>();

    public ObjectPlusPlus() {
        super();
    }

    public boolean isLink(String roleName, ObjectPlusPlus targetObject) {
        Map<Object, ObjectPlusPlus> objectLinks;

        if (!links.containsKey(roleName)) {
            return false;
        }

        objectLinks = links.get(roleName);

        return objectLinks.containsValue(targetObject);
    }

    //qualified association
    public void addLink(String roleName, String reverseRoleName,
                        ObjectPlusPlus targetObject, Object qualifier) {
        addLink(roleName, reverseRoleName, targetObject, qualifier, 2);
    }

    //ordinary association
    public void addLink(String roleName, String reverseRoleName,
                        ObjectPlusPlus targetObject) {
        addLink(roleName, reverseRoleName, targetObject, targetObject);
    }

    //"engine"
    private void addLink(String roleName, String reverseRoleName,
                         ObjectPlusPlus targetObject, Object qualifier, int counter) {

        Map<Object, ObjectPlusPlus> objectLinks;

        if (counter < 1) {
            return;
        }

        if (links.containsKey(roleName)) {
            objectLinks = links.get(roleName);
        } else {
            objectLinks = new HashMap<>();
            links.put(roleName, objectLinks);
        }

        if (!objectLinks.containsKey(qualifier)) {
            objectLinks.put(qualifier, targetObject);
            targetObject.addLink(reverseRoleName, roleName, this, this, counter - 1);
        }
    }

    public void removeLink(String roleName, String reverseRoleName,
                           ObjectPlusPlus targetObject, Object qualifier) {
        removeLink(roleName, reverseRoleName, targetObject, qualifier, 2);
    }

    public void removeLink(String roleName, String reverseRoleName,
                           ObjectPlusPlus targetObject) {
        removeLink(roleName, reverseRoleName, targetObject, targetObject, 2);
    }

    private void removeLink(String roleName, String reverseRoleName,
                            ObjectPlusPlus targetObject, Object qualifier, int counter) {

        if (counter < 1) {
            return;
        }

        if (!links.containsKey(roleName)) {
            return;
        }

        Map<Object, ObjectPlusPlus> objectLinks = links.get(roleName);

        if (!objectLinks.containsKey(qualifier)) {
            return;
        }

        if (objectLinks.get(qualifier) != targetObject) {
            return;
        }

        objectLinks.remove(qualifier);

        targetObject.removeLink(reverseRoleName, roleName, this, this, counter - 1);
    }

    public ObjectPlusPlus[] getLinks(String roleName) throws Exception {
        Map<Object, ObjectPlusPlus> objectLinks;

        if (!links.containsKey(roleName)) {
            throw new Exception("No links for the role: " + roleName);
        }

        objectLinks = links.get(roleName);
        return (ObjectPlusPlus[]) objectLinks.values().toArray(new ObjectPlusPlus[0]);
    }

    public void showLinks(String roleName, PrintStream stream) throws Exception {
        Map<Object, ObjectPlusPlus> objectLinks;

        if (!links.containsKey(roleName)) {
            // No links
            throw new Exception("No links for the role: " + roleName);
        }

        objectLinks = links.get(roleName);
        Collection col = objectLinks.values();

        stream.println(this.getClass().getSimpleName() + " links, role '" + roleName + "':");

        for (Object obj : col) {
            stream.println(" " + obj);
        }
    }

    public ObjectPlusPlus getLinkedObject(String roleName, Object qualifier) throws Exception {
        Map<Object, ObjectPlusPlus> objectLinks;

        if (!links.containsKey(roleName)) {
            throw new Exception("No links for the role: " + roleName);
        }

        objectLinks = links.get(roleName);

        if (!objectLinks.containsKey(qualifier)) {
            throw new Exception("No link for the qualifer: " + qualifier);
        }

        return objectLinks.get(qualifier);
    }
}