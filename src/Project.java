import java.time.LocalDate;

public class Project {
    
    private String projectId, projectName, projectDescription, projectType;     
    private Date projectStartDate, estimatedEndDate, actualEndDate;
    private boolean isActive = true;
    private static int projectCounter = 1000;

    LocalDate localDate = LocalDate.now();

    /** Constructor
     * 
     * @param projectName
     * @param projectDescription
     * @param projectStartDate
     * @param projectEndDate
     */
    public Project(String projectName, String projectDescription, 
    String projectStartDate, String projectEndDate) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectStartDate = new Date(projectStartDate);
        this.estimatedEndDate = new Date(projectEndDate);
        projectType = setProjectType();
        this.setProjectId();
    }

    // Get Methods
    public String getProjectId() {
        return this.projectId;
    }
    public String getProjectName() {
        return this.projectName;
    }
    public String getProjectDescription() {
        return this.projectDescription;
    }
    public String getProjectType() {
        return this.projectType;
    }

    public Date getProjectStartDate() {
        return this.projectStartDate;
    }
    public Date getEstimatedEndDate() {
        return this.estimatedEndDate;
    }
    public Date getActualEndDate() {
        return this.actualEndDate;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public int getProjectCounter() {
        return projectCounter;
    }

    // Set Methods
    public void setProjectStartDate(String date) {
        Date projectStartDate = new Date(date);
        if (projectStartDate.getYear() < 2000) {
            projectStartDate.setYear(localDate.getYear());
            projectStartDate.setMonth(localDate.getMonthValue());
            projectStartDate.setDay(localDate.getDayOfMonth());
        }
    }

    public void setEstimatedEndDate(String date) {
        Date estimatedEndDate = new Date(date);
        if (estimatedEndDate.isBefore(projectStartDate)) {
            estimatedEndDate = projectStartDate.addDays(30);
        }
    }

    public Date setActualEndDate(Date endDate) {
        if (endDate != null) {
            if (projectStartDate.isBefore(endDate)) {
                this.actualEndDate = endDate;
            }
        }
        else {
            actualEndDate = null;
        }
        return actualEndDate;
    }

    public String setProjectType() {
        String projectDuration;
        int daysBetween = projectStartDate.daysBetween(estimatedEndDate);
        if (daysBetween < 366) {
            projectDuration = "Short Term";
        }
        else if (daysBetween < 1096) {
            projectDuration = "Intermediate";
        }
        else {
            projectDuration = "Long Term";
        }
        daysBetween = 0;
        return projectDuration;
    }

    public String setProjectId() {
        projectId = "";
        if (projectType == "Short Term") {
            projectId += "ST-";
        }
        else if (projectType == "Short Term") {
            projectId += "IM-";
        }
        else {
            projectId += "LT-";
        }
        projectId += projectCounter;
        projectCounter++;
        return projectId;
    }
    // End of Set Methods

    public void deactivateProject(String date) {
        actualEndDate = new Date(date);
        this.setActualEndDate(actualEndDate);
        isActive = false;
    }

    public void activateProject() {
        this.actualEndDate = null;
        this.isActive = true;
    }

    public String pushProject(int days) {
        estimatedEndDate = estimatedEndDate.addDays(days);
        this.projectType = setProjectType();
        return this.projectType;
    }

    public int estimatedDaysToCompletion() {
        int estimatedDaysToCompletion = projectStartDate.daysBetween(estimatedEndDate);
        int val = estimatedDaysToCompletion;
        estimatedDaysToCompletion = 0;
        return val;
    }

    public boolean equals(Project project) {
        if (this.projectName == project.projectName) {
            return true; 
        }
        else {
            return false;
        }
    }

    public String toString() {
        String project = "";
        if (!isActive) {
            project =  
        "ID: " + "(" + projectId + ")\n" + projectName + "\n" + projectDescription + "\n" + 
        "COMPLETED: " + actualEndDate.toString();
        }
        else { 
            project =  
        "ID: " + "(" + projectId + ")\n" + projectName + "\n" + projectDescription + "\n" + 
        "Start: " + projectStartDate.toString() + " End: " + estimatedEndDate.toString(); 
        }
        return project;
    }
}
