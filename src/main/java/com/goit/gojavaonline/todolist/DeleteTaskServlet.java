package com.goit.gojavaonline.todolist;

import com.goit.gojavaonline.todolist.model.Task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by tamila on 9/14/16.
 */
public class DeleteTaskServlet extends HttpServlet {
    public static final String TODO_LIST = "todoList";
    public static final String UUID = "uuid";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String taskUuid = request.getParameter(UUID);
        List<Task> taskList = (List<Task>) session.getAttribute(TODO_LIST);
        deleteTaskByUuid(taskUuid, taskList);
        response.sendRedirect("todolist.jsp");
    }

    private void deleteTaskByUuid(String uuid, List<Task> tasksList) {
        for (Task task : tasksList) {
            if (task.getUuid().equals(uuid)) {
                tasksList.remove(task);
                return;
            }
        }
    }
}
