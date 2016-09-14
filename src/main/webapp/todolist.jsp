<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ToDo List</title>
</head>
<script>
    function onCheckBoxChange(checkBox, taskUuid) {
        var updateTaskForm = document.forms["formUpdateTask"];
        if ( updateTaskForm != null && updateTaskForm != undefined ) {
            var completed = checkBox.checked;

            var tagUuidInput = document.getElementById("updatedItemUuid");
            tagUuidInput.value = taskUuid;

            var updatedItemStateInput = document.getElementById("updatedItemState");
            updatedItemStateInput.value = "" + completed;

            updateTaskForm.submit();
        }
    }
</script>
<body>
<h2>My ToDo List</h2>

<form action="createTask" method="post">
    Task Name: <input type="text" name="taskName"/>
    <br/>
    Task Category: <input type="text" name="taskCategory"/>
    <input type="submit" value="Submit"/>
</form>

<table width="100%" border="1" align="center">
    <tr bgcolor="#5f9ea0">
        <th>Name</th>
        <th>Category</th>
        <th>Complete</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${todoList}" var="taskListItem">
        <tr>
            <td><c:out value="${taskListItem.name}"/></td>
            <td><c:out value="${taskListItem.category}"/></td>
            <td><input type="checkbox" <c:if  test="${taskListItem.complete}">checked="checked"</c:if> onchange="onCheckBoxChange(this, '<c:out value="${taskListItem.uuid}"/>');" /></td>
            <td><a href="deleteTask?uuid=${taskListItem.uuid}"> delete </a></td>
        </tr>
    </c:forEach>
</table>

<form id="formUpdateTask" action="updateTask" method="post">
    <input type="hidden" id="updatedItemState" name="completionState"/>
    <input type="hidden" id="updatedItemUuid" name="uuid"/>
</form>
</body>
</html>
