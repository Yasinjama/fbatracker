package com.fbatracker.rest.v1;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fbatracker.model.UserAccount;
import com.fbatracker.repository.UserAccountRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value="User Account resource",produces = "application/json")
public class AuthenticationResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountResource.class);
	
	@Autowired
	UserAccountRepository repository;
	
	@POST
	@Path("v1/authenticate/")
	@ApiOperation(value = "Authenticate User. Version 1",response = UserAccount.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Authenticate successful"),
			@ApiResponse(code = 400, message = "Authentication failed")
	})
	public UserAccount authenticate(@ApiParam @PathParam("username")String username, @ApiParam @PathParam("password")String password)
	{
		LOGGER.trace("getUserAccount username={}",username);
		UserAccount userAccount = repository.findUserAccountByUsername(username);
		if(userAccount == null)
		{
			throw new WebApplicationException("Authentication Failed",Response.Status.FORBIDDEN);
		}
		
		if(!userAccount.getPassword().equals(password))
		{
			throw new WebApplicationException("Authentication Failed",Response.Status.FORBIDDEN);
		}
		
		return userAccount;
	}
}
