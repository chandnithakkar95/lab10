<HTML>
<head>
<script>
// Get the modal
var modal = document.getElementById('id01');
var modal2 = document.getElementById('id02');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
	if (event.target == modal2) {
        modal2.style.display = "none";
    }
}
</script>
<link rel="stylesheet" href="Style.css">
</head>
<!-- Button to open the modal -->
<button onclick="document.getElementById('id01').style.display='block'">Sign Up</button>
<button onclick="document.getElementById('id02').style.display='block'">LOGIN</button>
<!-- The Modal (contains the Sign Up form) -->
<div id="id01" class="modal">
  <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
  <form class="modal-content animate" action="<%= application.getContextPath() %>/Controller?action=authlogin">
    <div class="container">
      <label><b>Email</b></label>
      <input type="text" placeholder="Enter Email" name="email" required>
      
      <label><b>Password</b></label>
      <input type="password" placeholder="Enter Password" name="psw" required>
<input type="hidden" name="submit" value="signup">
      <label><b>Repeat Password</b></label>
      <input type="password" placeholder="Repeat Password" name="psw-repeat" required>
      <input type="checkbox" checked="checked"> Remember me
	<div class="clearfix">
        <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
        <button type="submit" class="signupbtn" >Submit</button>
      </div>
    </div>
  </form>
</div>


<div id="id02" class="modal">
  <span onclick="document.getElementById('id02').style.display='none'" class="close" title="Close Modal">&times;</span>
  <form class="modal-content animate" method="post"action="<%=application.getContextPath() %>/Controller?action=authlogin">
    <div class="container">
      <label><b>Email</b></label>
      <input type="text" placeholder="Enter Email" name="email" required>

      <label><b>Password</b></label>
      <input type="password" placeholder="Enter Password" name="psw" required>

      <input type="hidden" name="submit" value="login">
      <div class="clearfix">
        <button type="button" onclick="document.getElementById('id02').style.display='none'" class="cancelbtn">Cancel</button>
        <button type="submit" class="signupbtn">LOGIN</button>
      </div>
    </div>
  </form>
</div>

</HTML>