package com.massivecraft.massivecore.command.type.enumeration;

import org.bukkit.entity.Cat.Type;

public class TypeCatType extends TypeEnum<Type>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static TypeCatType i = new TypeCatType();
	public static TypeCatType get() { return i; }
	public TypeCatType()
	{
		super(Type.class);
	}

}
