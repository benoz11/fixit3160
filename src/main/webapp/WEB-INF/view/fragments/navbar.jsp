<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="page" value="${requestScope['javax.servlet.forward.request_uri']}" />

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <ul class="navbar-nav">
  
  	<li class="nav-item">
  		<a href="/"><img class="nav-link" src="/resources/img/logo.png" alt="FixIT" /></a>
  	</li>
    
    <li class="nav-item">
    	<a class="nav-link${page.endsWith('/tickets') ? ' active' : ''}" href="/tickets">Tickets</a>
    </li>
    
    <li class="nav-item">
    	<a class="nav-link${page.endsWith('/users') ? ' active' : ''}" href="/users">Users</a>
    </li>
    
    <li class="nav-item">
      	<a class="nav-link${page.endsWith('/admin') ? ' active' : ''}" href="/admin">Admin</a>
    </li>
    
    <li class="nav-item">
      	<a class="nav-link disabled" href="/abcefgh">Disabled</a>
    </li>
    
    <li class="nav-item">
      <form class="nav-link" name='logoutForm' action="<c:url value='/logout' />" method='POST'> 
     	 <input type="hidden" name="${_csrf.parameterName}"value="${_csrf.token}" />
     	 <input name="submit" type="submit" value="Logout" />
      </form>
    </li>
    
  </ul>
</nav>