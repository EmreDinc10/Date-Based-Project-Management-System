public class ProjectApp {
    public static void main(String[] args) {

        Project project1 = new Project("Sys-Renew", "Upgrade payroll system and hardware", "2022-5-22", "2022-6-21");
        System.out.println("Project 1:");
        System.out.println(project1);
        Project project2 = new Project("LAB-IMPLEMENT", "Update Lab Results Reports", "2023-5-22", "2023-9-30");
        System.out.println();
        System.out.println("Project 2:");
        System.out.println(project2);
        System.out.println();
        System.out.println("5 days late:");
        project1.pushProject(5);
        System.out.println(project1);

        System.out.println();
        System.out.println("50 days late: ");
        project1.pushProject(50);
        System.out.println(project1);

        System.out.println("395 days late:");
        project1.pushProject(395);
        System.out.println(project1);

        System.out.println();
        System.out.println("Deactivated: ");
        project1.deactivateProject("2023-10-15");
        System.out.println(project1);

        System.out.println();
        System.out.println("Activated");
        project1.activateProject();
        System.out.println(project1);
    }
}
