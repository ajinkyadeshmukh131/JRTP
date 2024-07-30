<%@page isELIgnored="false" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="frm"%>

<h1 style="color:blue;text-align:center">Employee Edit Form </h1>

<frm:form modelAttribute="emp">
   <table border="0" bgcolor="yellow" align="center">
     <tr>
         <td> Employee no:: </td>
          <td> <frm:input path="empno" readonly="true"/> </td>
       </tr>
       <tr>
         <td> Employee name:: </td>
          <td> <frm:input path="ename"/> </td>
       </tr>
         <tr>
         <td> Employee JOB:: </td>
          <td> <frm:input path="job"/> </td>
       </tr>
        <tr>
         <td> Employee Salary:: </td>
          <td> <frm:input path="sal"/> </td>
       </tr>
        <tr>
         <td><input type="submit"  value="Edit Employee"></td>
       </tr>
   </table>

</frm:form> 

