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
package gplx.gfui.layouts; import gplx.*; import gplx.gfui.*;
public class GftCell {
	public GftSizeCalc Len0() {return len0;} public GftCell Len0_(GftSizeCalc c) {len0 = c; return this;} GftSizeCalc len0 = new GftSizeCalc_num(1);
	public GftCell Clone() {
		GftCell rv = new GftCell();
		rv.len0 = len0.Clone();
		return rv;
	}		
}