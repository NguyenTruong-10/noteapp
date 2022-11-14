package truongducnguyen.aprotrain.com.models;

public class Mytask {
    private int taskId;
    private String taskTitle;
    private String taskContent;
    private Boolean isImportant;

    public Mytask(int taskId, String taskTitle, String taskContent, Boolean isImportant) {
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.taskContent = taskContent;
        this.isImportant = isImportant;
    }
    public Mytask(){

    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public Boolean getImportant() {
        return isImportant;
    }

    public void setImportant(Boolean important) {
        isImportant = important;
    }




}
