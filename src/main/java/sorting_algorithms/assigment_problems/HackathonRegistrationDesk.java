package sorting_algorithms.assigment_problems;

public class HackathonRegistrationDesk {

    public static class Team {
        public String name;
        public int size;

        public Team(String name, int size) {
            this.name = name;
            this.size = size;
        }

        @Override
        public String toString() {
            return name + "(" + size + ")";
        }
    }

    /**
     * Sorts teams in place by team size ascending using Insertion Sort.
     * Preserves registration order for teams of equal size (stable).
     *
     * Time Complexity:
     *   - Best Case: O(n)
     *   - Average / Worst Case: O(n^2)
     * Extra Space: O(1) auxiliary space (in-place sort).
     * Stability: Stable (uses strictly greater comparison during shifting).
     *
     * @param teams array of Team objects
     */
    public static void sortTeams(Team[] teams) {
        if (teams == null || teams.length <= 1) {
            return;
        }

        for (int i = 1; i < teams.length; i++) {
            Team key = teams[i];
            int j = i - 1;

            // Shift earlier teams right only while their size is strictly greater
            while (j >= 0 && teams[j].size > key.size) {
                teams[j + 1] = teams[j];
                j--;
            }
            teams[j + 1] = key;
        }
    }

    public static void printTeams(Team[] teams) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < teams.length; i++) {
            sb.append(teams[i]);
            if (i < teams.length - 1) {
                sb.append(", ");
            }
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        Team[] teams = {
            new Team("Pixel", 4),
            new Team("Byte", 2),
            new Team("Nova", 4),
            new Team("Echo", 3),
            new Team("Atom", 2)
        };

        sortTeams(teams);
        printTeams(teams);
    }
}
