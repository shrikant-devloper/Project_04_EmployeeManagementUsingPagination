
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose>
    <c:when test="${not empty empsData.getContent()}">
        <h1 style="color: red; text-align: center">Employee Report</h1>
        <h2 style="text-align: center; color: green">${resultMsg }</h2>
        <table border="1" align="center" bgcolor="cyan">
        
            <tr style="color: red">
                <th>empno</th>
                <th>ename</th>
                <th>job</th>
                <th>sal</th>
                <th>deptno</th>
                <th>operations</th>
            </tr>
            <c:forEach var="emp" items="${empsData.getContent()}">
                <tr style="color: blue">
                    <td>${emp.empno }</td>
                    <td>${emp.ename }</td> 
                    <td>${emp.job }</td>
                    <td>${emp.sal }</td>
                    <td>${emp.deptno }</td>
                    <td><a href="emp_edit?no=${emp.empno }"><img src="images/edit.png" width="30px" height="30px"></a>
                    <a href="emp_delete?no=${emp.empno }" onclick="return confirm('Do u want to delete the employee')"><img src="images/delete.png" width="30px" height="30px"></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p style="text-align: center">
          <c:if test="${empsData.hasPrevious() }">
          <a href="report?page=${empsData.getPageable().getPageNumber()-1 }">Previous</a>&nbsp;&nbsp;&nbsp;
          </c:if>
          
          <c:if test="${empsData.isFirst() }">
          <a href="report?page=0">First</a>&nbsp;&nbsp;&nbsp;
          </c:if>
          <c:forEach var="i" begin="1" end="${empsData.getTotalPages() }" step="1">
          [<a href="report?page=${i-1 }">${i }</a>]&nbsp;&nbsp;
          </c:forEach>
          <c:if test="${empsData.isLast() }">
          <a href="report?page=${empsData.getTotalPages()-1 }">Last</a>&nbsp;&nbsp;&nbsp;
          </c:if>
          <c:if test="${empsData.hasNext() }">
          <a href="report?page=${empsData.getPageable().getPageNumber()+1 }">Next</a>&nbsp;&nbsp;&nbsp;
          </c:if>
        </p>
    </c:when>
    <c:otherwise>
        <h1 style="color:red; text-align: center">Employee Not Found</h1>
    </c:otherwise>
</c:choose>
<center>
<a href="emp_add"><img src="images/add.png" width="40px" height="50px">Add Employee</a>&nbsp;&nbsp;&nbsp;
<a href="./"><img src="images/home.png" width="40px" height="50px">Home</a>
</center>










