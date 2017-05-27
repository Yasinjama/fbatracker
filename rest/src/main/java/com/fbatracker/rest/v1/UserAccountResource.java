package com.fbatracker.rest.v1;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
public class UserAccountResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountResource.class);
	
	@Autowired
	UserAccountRepository repository;
	
	@GET
	@Path("v1/useraccount/{username}")
	@ApiOperation(value="Get a User Account resource. Version 1",response = UserAccount.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "User account found"),
			@ApiResponse(code = 404, message = "Given username not found")
	})
	public UserAccount getUserAccount(@ApiParam @PathParam("username")String username)
	{
		LOGGER.trace("getUserAccount username={}",username);
		UserAccount userAccount = repository.findUserAccountByUsername(username);
		if(userAccount == null)
		{
			throw new WebApplicationException("User account not found",Response.Status.NOT_FOUND);
		}
		return userAccount;
	}
	
	@POST
	@Path("v1/useraccount/")
	@ApiOperation(value = "Create User account resource. Version 1",response = UserAccount.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "User account created"),
			@ApiResponse(code = 400, message = "Invalid request")
	})
	public UserAccount createUserAccount(UserAccount userAccount)
	{
		UserAccount user = repository.findUserAccountByUsername(userAccount.getUsername());
		
		if(user != null)
		{
			throw new WebApplicationException("User account with username "+userAccount.getUsername()+" exist already",Response.Status.BAD_REQUEST);
		}
		
		return repository.save(userAccount);
	}
	
	@PUT
	@Path("v1/useraccount/{username}")
	@ApiOperation(value = "Update User account resource. Version 1",response = UserAccount.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "User account updated"),
			@ApiResponse(code = 404, message = "User account not found")
	})
	public UserAccount updateUserAccount(@ApiParam @PathParam("username")String usernname,UserAccount userAccount)
	{
		UserAccount user = repository.findUserAccountByUsername(usernname);
		if(user == null)
		{
			throw new WebApplicationException("User account not found",Response.Status.NOT_FOUND);
		}
		userAccount.setId(user.getId());
		return repository.save(userAccount);	
	}
	
	@DELETE
	@Path("v1/useraccount/{username}")
	@ApiOperation(value = "Delete User account resource. Version 1",response = UserAccount.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "User account deleted"),
			@ApiResponse(code = 404, message = "User account not found")
	})
	public Response deleteUserAccount(@ApiParam @PathParam("username")String username)
	{
		UserAccount user = repository.findUserAccountByUsername(username);
		if(user == null)
		{
			throw new WebApplicationException("User account not found",Response.Status.NOT_FOUND);
		}
		
		repository.delete(user);
		
		return Response.ok().build();
	}
}
