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
public class UpdateTaskServlet extends HttpServlet {
    public static final String TODO_LIST = "todoList";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String taskUuid = request.getParameter("uuid");
        String completed = request.getParameter("completionState");
        HttpSession session = request.getSession(true);
        List<Task> taskList = (List<Task>) session.getAttribute(TODO_LIST);
        for (Task task : taskList) {
            if(task.getUuid().equals(taskUuid)) {
                task.setComplete(completed.equals("true"));
            }
        }
        response.sendRedirect("todolist.jsp");
    }
}
