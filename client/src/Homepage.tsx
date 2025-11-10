import './css/Homepage.css'
import { useNavigate } from 'react-router-dom';

export default function Homepage() {
  const navigate = useNavigate();

  // Login button redirect is temporary
  return (
    <div className="main-wrapper">
      <div className='content'>
        <h1 className="headermain">Welcome to the future of pickup basketball.</h1>
        <div className="form-container">
          <form className="hpgauth">
            <label htmlFor="username">Email Address</label> 
            <input type="text" className="username"/> 
            <label htmlFor="username">Password</label>
            <input type="text" className="password"/>
            <button className="login-button" onClick={() => navigate('/games')}>Log In!</button> 
            <span className="or-button">or</span>
            <button className="make-account" onClick={() => navigate('/signup')}>Sign Up!</button>
          </form>
        </div>
      </div>
    </div>
  );
}