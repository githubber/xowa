/*
XOWA: the XOWA Offline Wiki Application
Copyright (C) 2012 gnosygnu@gmail.com

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as
published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package gplx.core.tests; import gplx.*; import gplx.core.*;
import gplx.core.brys.*;
public class Gftest {
	private static final    Bry_bfr bfr = Bry_bfr.new_();
	public static void Eq__ary(String[] expd, byte[][] actl, String msg_fmt, Object... msg_args) {Eq__ary(Bry_.Ary(expd), actl, msg_fmt, msg_args);}
	public static void Eq__ary(Bry_bfr_able[] expd_ary, Bry_bfr_able[] actl_ary) {Eq__ary(expd_ary, actl_ary, null);}
	public static void Eq__ary(Bry_bfr_able[] expd_ary, Bry_bfr_able[] actl_ary, String msg_fmt, Object... msg_args) {
		Eq__ary(Bry_bfr_able_.To_bry_ary(bfr, expd_ary), Bry_bfr_able_.To_bry_ary(bfr, actl_ary), msg_fmt, msg_args);
	}
	public static void Eq__ary(byte[][] expd_bry_ary, byte[][] actl_bry_ary, String msg_fmt, Object... msg_args) {
		boolean[] failures = Calc__failures(Type_adp_.Tid__bry, expd_bry_ary, actl_bry_ary);
		if (failures != null) {
			Write_fail_head(bfr, msg_fmt, msg_args);
			Write_fail_ary(bfr, failures, Type_adp_.Tid__bry, expd_bry_ary, actl_bry_ary);
			throw Err_.new_wo_type(bfr.To_str_and_clear());
		}
	}
	public static void Eq__str(String expd, String actl) {Eq__str(expd, actl, null);}
	public static void Eq__str(String expd, String actl, String msg_fmt, Object... msg_args) {
		if (String_.Eq(expd, actl)) return;
		Write_fail_head(bfr, msg_fmt, msg_args);
		bfr.Add_str_a7("expd: ").Add_str_u8(expd).Add_byte_nl();
		bfr.Add_str_a7("actl: ").Add_str_u8(actl).Add_byte_nl();
		bfr.Add(Bry__line_end);
		throw Err_.new_wo_type(bfr.To_str_and_clear());
	}
	public static void Eq__bry(byte[] expd, byte[] actl) {Eq__bry(expd, actl, null);}
	public static void Eq__bry(byte[] expd, byte[] actl, String msg_fmt, Object... msg_args) {
		if (Bry_.Eq(expd, actl)) return;
		Write_fail_head(bfr, msg_fmt, msg_args);
		bfr.Add_str_a7("expd: ").Add(expd).Add_byte_nl();
		bfr.Add_str_a7("actl: ").Add(actl).Add_byte_nl();
		bfr.Add(Bry__line_end);
		throw Err_.new_wo_type(bfr.To_str_and_clear());
	}
	private static void Write_fail_head(Bry_bfr bfr, String msg_fmt, Object[] msg_args) {
		bfr.Add(Bry__line_bgn);
		if (msg_fmt != null) {
			bfr.Add_str_u8(String_.Format(msg_fmt, msg_args));
			bfr.Add(Bry__line_mid);
		}
	}
	private static void Write_fail_ary(Bry_bfr bfr, boolean[] failures, int type_id, Object expd_ary, Object actl_ary) {	
		int len = failures.length;
		int expd_len = Array_.Len(expd_ary);
		int actl_len = Array_.Len(actl_ary);
		for (int i = 0; i < len; ++i) {
			boolean failure = failures[i];
			int pad_len = 5 - Int_.DigitCount(i);
			bfr.Add_int_pad_bgn(Byte_ascii.Num_0, pad_len, i).Add_byte_colon().Add_byte_space();
			Write__itm(bfr, type_id, expd_ary, expd_len, i);
			if (failure) {
				bfr.Add(Bry__item__eq_n).Add_byte_repeat(Byte_ascii.Space, pad_len - 1);
				Write__itm(bfr, type_id, actl_ary, actl_len, i);
			}
		}
		bfr.Add(Bry__line_end);
	}
	private static void Write__itm(Bry_bfr bfr, int type_id, Object ary, int len, int idx) {
		if (idx < len) {
			switch (type_id) {
				case Type_adp_.Tid__bry: bfr.Add((byte[])Array_.Get_at(ary, idx)); break;
				default: throw Err_.new_unhandled_default(type_id);
			}
		}
		else
			bfr.Add(Bry__null);
		bfr.Add_byte_nl();
	}
	private static boolean[] Calc__failures(int tid, Object expd_ary, Object actl_ary) {	
		int expd_len = Array_.Len(expd_ary);
		int actl_len = Array_.Len(actl_ary);
		int max_len = expd_len > actl_len ? expd_len : actl_len; if (max_len == 0) return null;
		boolean[] rv = null;
		for (int i = 0; i < max_len; ++i) {
			Object expd_obj = i < expd_len ? Array_.Get_at(expd_ary, i) : null;
			Object actl_obj = i < actl_len ? Array_.Get_at(actl_ary, i) : null;
			boolean eq = false;
			switch (tid) {
				case Type_adp_.Tid__bry:		eq = Bry_.Eq((byte[])expd_obj, (byte[])actl_obj); break;
			}
			if (!eq) {
				if (rv == null) {
					rv = new boolean[max_len];
				}
				rv[i] = true;
			}
		}
		return rv;
	}
	private static final    byte[] Bry__item__eq_n = Bry_.new_a7("!= ") // Bry__item__eq_y = Bry_.new_a7("== "), 
	, Bry__null = Bry_.new_a7("<<NULL>>")
	, Bry__line_bgn = Bry_.new_a7("\n************************************************************************************************\n")
	, Bry__line_mid = Bry_.new_a7("\n------------------------------------------------------------------------------------------------\n")
	, Bry__line_end = Bry_.new_a7(  "________________________________________________________________________________________________")
	;
}
/*
package ns;
import org.junit.*; import gplx.core.tests.*;
public class Cls1_tst {
	private final    Cls1_fxt fxt = new Cls1_fxt();
	@Test 	public void Basic() {}
}
class Cls1_fxt {
	private final    Cls1 mgr = new Cls1();
	public Cls1_fxt Test() {return this;}
}
*/