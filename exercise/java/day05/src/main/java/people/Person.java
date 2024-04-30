package people;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

public record Person(String firstName, String lastName, List<Pet> pets) {
    public Person(String firstName, String lastName) {
        this(firstName, lastName, new ArrayList<>());
    }

    public Person addPet(PetType petType, String name, int age) {
        pets.add(new Pet(petType, name, age));
        return this;
    }

    public String format() {
        return FormattedName.withName(this.firstName, this.lastName).havingPets(pets).print();
    }

    private record FormattedName(String fullName, List<Pet> pets) {

        public static FormattedName withName(String firstName, String lastName) {
            return new FormattedName(fullName(firstName, lastName), Collections.emptyList());
        }

        public static String fullName(String firstName, String lastName) {
            return firstName + " " + lastName;
        }

        public FormattedName havingPets(List<Pet> pets) {
            return new FormattedName(this.fullName, pets);
        }

        public String print() {
            return this.pets.stream()
                            .map(Pet::name)
                            .reduce(FormattedName::fullName)
                            .map(petName -> this.fullName + " who owns : " + petName)
                            .orElse(this.fullName);
        }
    }

}
