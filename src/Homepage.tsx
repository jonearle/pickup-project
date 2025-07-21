import './css/Homepage.css'
import { useNavigate } from 'react-router-dom';

export default function Homepage() {
  const navigate = useNavigate();
  const tempRedirect = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    navigate('/games')
  };
  return (
    <div className="main-wrapper">
      <div className='content'>
        <h1 className="headermain">Welcome to the future of pickup basketball.</h1>
        <div className="form-container">
          <form className="hpgauth" onSubmit={tempRedirect}>
            <label htmlFor="username">Email Address</label> 
            <input type="text" className="username"/> 
            <label htmlFor="username">Password</label>
            <input type="text" className="password"/>
            <button className="login-button">Log In!</button>
          </form>
        </div>
      </div>
    </div>
  );
}