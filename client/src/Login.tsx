/* Clone of the homepage but with a different header */
import './css/Homepage.css'
import { useNavigate } from 'react-router-dom';

export default function Homepage() {
  const navigate = useNavigate();

  return (
    <div className="main-wrapper">
      <div className='content'>
        <h1 className="headermain">Welcome back.</h1>
        <div className="form-container">
          <form className="hpgauth">
            <label htmlFor="username">Email Address</label> 
            <input type="text" className="username"/> 
            <label htmlFor="username">Password</label>
            <input type="text" className="password"/>
            <button className="login-button" onClick={() => navigate('/games')}>Log In!</button>
          </form>
        </div>
      </div>
    </div>
  );
}