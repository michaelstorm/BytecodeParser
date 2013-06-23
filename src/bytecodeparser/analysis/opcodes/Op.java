/*
 *  Copyright (C) 2011 Stephane Godbillon
 *  
 *  This file is part of BytecodeParser. See the README file in the root
 *  directory of this project.
 *
 *  BytecodeParser is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  BytecodeParser is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.

 *  You should have received a copy of the GNU Lesser General Public License
 *  along with BytecodeParser.  If not, see <http://www.gnu.org/licenses/>.
 */
package bytecodeparser.analysis.opcodes;

import bytecodeparser.Context;
import bytecodeparser.analysis.Opcodes;
import bytecodeparser.analysis.Opcodes.OpParameterType;
import bytecodeparser.analysis.decoders.DecodedOp;

public abstract class Op {
	/**
	 * The opcode int.
	 */
	public final int code;
	protected final OpParameterType[] parameterTypes;
	private String name;
	protected Op(int code, OpParameterType... opParameterTypes) {
		this.code = code;
		this.parameterTypes = opParameterTypes;
	}
	
	/**
	 * Decodes this op with a given context.
	 * @param context
	 * @param index
	 * @return the matching decodedOp.
	 */
	public abstract DecodedOp decode(Context context, int index);
	
	/**
	 * Should be called before using this object.
	 * @return this object's copy with some contextual information, if needed.
	 */
	public Op init(Context context, int index) {
		return this;
	}
	
	/**
	 * @return the opcode int.
	 */
	public int getCode() {
		return code;
	}
	
	/**
	 * @return the paramaters types of this op, if any.
	 */
	public OpParameterType[] getParameterTypes() {
		return parameterTypes;
	}
	
	/**
	 * @return the name of this opcode.
	 */
	public String getName() {
		if(name == null)
			name = Opcodes.findOpName(code);
		return name;
	}
	
	/**
	 * Shortcut for chaining and casting.
	 * @param specificOpClass
	 * @return the casted op.
	 */
	@SuppressWarnings(value="unchecked")
	public <T extends Op> T as(Class<T> specificOpClass) {
		return (T) this;
	}
	
	@Override
	public String toString() {
		return "op: " + getName() + "";
	}
}