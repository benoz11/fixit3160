<!--
* navbar.jsp
* Project: fixit3160
*		An IT help ticketing support system developed using Spring
*
*    SENG3160 University of Newcastle 2019
*
*    Benjamin McDonnell, Matthew Rudge, Jordan Maddock, Kundayi Sitole
* Code for the navigation bar that sits at the top of the page and looks the same on every jsp
-->

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="page" value="${requestScope['javax.servlet.forward.request_uri']}" />

<nav class="navbar navbar-expand-sm fixit-bg-blue navbar-dark my-auto">
  <ul class="navbar-nav"> <!--navbar is formatted as an unordered list-->

  	<li class="nav-item">
  		<a href="/"><img class="nav-link" src="/resources/img/logo_white_small.png" alt="FixIT" /></a>
  	</li>

    <li class="nav-item">
    	<a class="nav-link${page.endsWith('/tickets') ? ' active' : ''}" href="/tickets">Tickets</a>
    </li>

     <li class="nav-item">
         <a class="nav-link${page.endsWith('/knowledgeBase') ? ' active' : ''}" href="/knowledgeBase">
             Knowledge Base
         </a>
     </li>
     
	<sec:authorize access="hasRole('Manager')">
    <li class="nav-item">
    	<a class="nav-link${page.endsWith('/users') ? ' active' : ''}" href="/users">Users</a>
    </li>
    </sec:authorize>
    
    <li class="nav-item">
        <a class="nav-link${page.endsWith('/myprofile') ? ' active' : ''}" href="/myprofile">
             My Profile
        </a>
     </li>

	<!--  
    <li class="nav-item">
      	<a class="nav-link disabled" href="/abcefgh">Disabled</a>
    </li>
    -->

    <li class="nav-item">
      <form name='logoutForm' action='/logout' method='POST'>
     	 <input type="hidden" name="${_csrf.parameterName}"value="${_csrf.token}" />
     	 <input class="btn btn-link nav-link" name="submit" type="submit" value="Logout" />
      </form>
    </li>

  </ul>
</nav>