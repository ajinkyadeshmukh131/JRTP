<%@ page isELIgnored="false"   %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<c:choose>
  <c:when test="${!empty empsInfo}">
    <table bgcolor="cyan" border="1"  align="center">
           <tr bgcolor="yellow">
             <th>EMPNO</th><th>ENAME</th><th>JOB</th><th>SALARY</th> <th>OPERATIONS</th>
         </tr>
         <c:forEach var="emp" items="${empsInfo}">
            <tr bgcolor="pink">
               <td>${emp.empno}</td>
                <td>${emp.ename}</td>
                 <td>${emp.job}</td>
                <td>${emp.sal}</td>
                <td><a href="edit?no=${emp.empno}"><img src="images/edit.png" width="30" height="30"></a>
                        <a href="delete?no=${emp.empno}"><img src="images/delete.jpg" width="30" height="30"  onclick="return confirm('are sure to delete the reccord?')"></a>
                 </td>
            </tr>
         </c:forEach>
    </table>
  </c:when>
  <c:otherwise>
     
     <h1 style="color:red;text-align:center">Records not found</h1>
  
  </c:otherwise>


</c:choose>
                   <h2 style="text-align:center;color:green">${resultMsg}</h2>
<h1 style="color:red;text-align:center"><a href="register"><img src="images/add.jpg" width="50" height="50"></a> &nbsp;&nbsp;&nbsp;
                    <a href="./"><img src="images/home.jpeg" width="50" height="50"></a> </h1>

