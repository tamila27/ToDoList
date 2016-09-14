package com.goit.gojavaonline.todolist;

import com.goit.gojavaonline.todolist.model.Task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by tamila on 9/14/16.
 */
public class CreateTaskServlet extends HttpServlet {

    public static final String TODO_LIST = "todoList";
    public static final String TASK_NAME = "taskName";
    public static final String TASK_CATEGORY = "taskCategory";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession(true);
        createTask(request, session);
        response.sendRedirect("todolist.jsp");
    }

    private void createTask(HttpServletRequest request, HttpSession session) {
        List<Task> taskList = (List<Task>) session.getAttribute(TODO_LIST);
        if (taskList == null) {
            taskList = new ArrayList<>();
            session.setAttribute(TODO_LIST, taskList);
        }
        Task task = new Task();
        task.setUuid(UUID.randomUUID().toString());
        task.setName(request.getParameter(TASK_NAME));
        task.setCategory(request.getParameter(TASK_CATEGORY));
        taskList.add(task);
    }

}
