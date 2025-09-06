import React, { createContext, useContext, useState, useEffect } from 'react';
import api from '../api/axios';

const AuthContext = createContext();

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
};

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    // Check if user is logged in on app start
    const token = localStorage.getItem('token');
    const userData = localStorage.getItem('user');
    
    if (token && userData) {
      setUser(JSON.parse(userData));
    }
    setLoading(false);
  }, []);

  const signup = async (userData) => {
    try {
      // Demo mode - simulate successful signup
      console.log('Demo mode: Signup successful for', userData.email);
      
      // Simulate API delay
      await new Promise(resolve => setTimeout(resolve, 1000));
      
      // Return success response
      return { 
        message: 'Account created successfully! Please login with your credentials.',
        user: {
          id: Date.now(),
          name: userData.name,
          email: userData.email,
          phone: userData.phone
        }
      };
    } catch (error) {
      throw error.response?.data || { message: 'Signup failed' };
    }
  };

  const login = async (credentials) => {
    try {
      // Demo mode - simulate successful login
      console.log('Demo mode: Login attempt for', credentials.email);
      
      // Simulate API delay
      await new Promise(resolve => setTimeout(resolve, 1000));
      
      // Demo credentials (any email/password combination works)
      const demoUser = {
        id: Date.now(),
        name: 'Demo User',
        email: credentials.email,
        phone: '+1 (555) 123-4567'
      };
      
      const demoToken = 'demo-jwt-token-' + Date.now();
      
      localStorage.setItem('token', demoToken);
      localStorage.setItem('user', JSON.stringify(demoUser));
      setUser(demoUser);
      
      return {
        token: demoToken,
        user: demoUser,
        message: 'Login successful!'
      };
    } catch (error) {
      throw error.response?.data || { message: 'Login failed' };
    }
  };

  const logout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    setUser(null);
  };

  const value = {
    user,
    signup,
    login,
    logout,
    loading
  };

  return (
    <AuthContext.Provider value={value}>
      {children}
    </AuthContext.Provider>
  );
};
