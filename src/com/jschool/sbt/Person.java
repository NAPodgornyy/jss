package com.jschool.sbt;

class Main {
    public static void main(String[] args) {
        Person p1 = new Person(true, "Liza");
        Person p2 = new Person(true, "Katy");
        Person p3 = new Person(false, "Max");
        Person p4 = new Person(false, "John");

        System.out.println(p1.marry(p3));
    }
}

public class Person {
    private final boolean man;
    private final String name;
    private Person spouse;


    public Person(boolean man, String name) {
        this.man = man;
        this.name = name;
    }

    /**
     *      
     * This method checks gender of persons. If genders are not equal - tries to marry.
     *  If one of them has another spouse - execute divorce(sets spouse = null for husband and wife. Example: if both persons have spouses - then divorce will set 4 spouse to null) and then executes marry().
     *
     * @return - returns true if this person has another gender than passed person and they are not husband and wife, false otherwise
     * @param person - new husband/wife for this person.
     */

    public boolean marry(Person person) {
        if (this.man == person.man) return false;
        if (this.spouse == person.spouse && this.spouse != null && person.spouse != null) return false;
        if (this.spouse != null) this.divorce();
        if (person.spouse != null) person.divorce();
        this.spouse = person;
        person.spouse = this;
        return true;
    }

    /**
     *      * Sets spouse = null if spouse is not null
     *      * @return true - if person status has been changed
     *      
     */

    public boolean divorce() {
        if (this.spouse != null) {
            this.spouse.spouse = null;
            this.spouse = null;
            return true;
        } else {
            return false;
        }
    }

}


