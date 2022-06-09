export default function authHeader() {
 const user = JSON.parse(localStorage.getItem("empleado"));

 if (user && user.accessToken) {
  return { Authorization: "Bearer " + user.accessToken };
 } else {
  return {};
 }
}
