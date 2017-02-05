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
package gplx.xowa.mws.media; import gplx.*; import gplx.xowa.*; import gplx.xowa.mws.*;
import gplx.xowa.mws.filerepo.file.*;
public class Xomw_ImageHandler extends Xomw_MediaHandler {	public Xomw_ImageHandler(byte[] key) {super(key);}
	/**
	* @param File file
	* @return boolean
	*/
	@Override public boolean canRender(Xomw_File file) {
		return (file.getWidth(1) != -1 && file.getHeight(1) != -1);
	}

//		public function getParamMap() {
//			return [ 'img_width' => 'width' ];
//		}
//
//		public function validateParam(name, value) {
//			if (in_array(name, [ 'width', 'height' ])) {
//				if (value <= 0) {
//					return false;
//				} else {
//					return true;
//				}
//			} else {
//				return false;
//			}
//		}
//
//		public function makeParamString(params) {
//			if (isset(params['physicalWidth'])) {
//				width = params['physicalWidth'];
//			} elseif (isset(params['width'])) {
//				width = params['width'];
//			} else {
//				throw new MediaTransformInvalidParametersException('No width specified to ' . __METHOD__);
//			}
//
//			# Removed for ProofreadPage
//			# width = intval(width);
//			return "{width}px";
//		}
//
//		public function parseParamString(str) {
//			m = false;
//			if (preg_match('/^(\d+)px/', str, m)) {
//				return [ 'width' => m[1] ];
//			} else {
//				return false;
//			}
//		}
//
//		function getScriptParams(params) {
//			return [ 'width' => params['width'] ];
//		}
//
//		/**
//		* @param File image
//		* @param array params
//		* @return boolean
//		*/
//		function normaliseParams(image, &params) {
//			mimeType = image.getMimeType();
//
//			if (!isset(params['width'])) {
//				return false;
//			}
//
//			if (!isset(params['page'])) {
//				params['page'] = 1;
//			} else {
//				params['page'] = intval(params['page']);
//				if (params['page'] > image.pageCount()) {
//					params['page'] = image.pageCount();
//				}
//
//				if (params['page'] < 1) {
//					params['page'] = 1;
//				}
//			}
//
//			srcWidth = image.getWidth(params['page']);
//			srcHeight = image.getHeight(params['page']);
//
//			if (isset(params['height']) && params['height'] != -1) {
//				# Height & width were both set
//				if (params['width'] * srcHeight > params['height'] * srcWidth) {
//					# Height is the relative smaller dimension, so scale width accordingly
//					params['width'] = self::fitBoxWidth(srcWidth, srcHeight, params['height']);
//
//					if (params['width'] == 0) {
//						# Very small image, so we need to rely on client side scaling :(
//						params['width'] = 1;
//					}
//
//					params['physicalWidth'] = params['width'];
//				} else {
//					# Height was crap, unset it so that it will be calculated later
//					unset(params['height']);
//				}
//			}
//
//			if (!isset(params['physicalWidth'])) {
//				# Passed all validations, so set the physicalWidth
//				params['physicalWidth'] = params['width'];
//			}
//
//			# Because thumbs are only referred to by width, the height always needs
//			# to be scaled by the width to keep the thumbnail sizes consistent,
//			# even if it was set inside the if block above
//			params['physicalHeight'] = File::scaleHeight(srcWidth, srcHeight,
//				params['physicalWidth']);
//
//			# Set the height if it was not validated in the if block higher up
//			if (!isset(params['height']) || params['height'] == -1) {
//				params['height'] = params['physicalHeight'];
//			}
//
//			if (!this.validateThumbParams(params['physicalWidth'],
//				params['physicalHeight'], srcWidth, srcHeight, mimeType)
//			) {
//				return false;
//			}
//
//			return true;
//		}
//
//		/**
//		* Validate thumbnail parameters and fill in the correct height
//		*
//		* @param int width Specified width (input/output)
//		* @param int height Height (output only)
//		* @param int srcWidth Width of the source image
//		* @param int srcHeight Height of the source image
//		* @param String mimeType Unused
//		* @return boolean False to indicate that an error should be returned to the user.
//		*/
//		function validateThumbParams(&width, &height, srcWidth, srcHeight, mimeType) {
//			width = intval(width);
//
//			# Sanity check width
//			if (width <= 0) {
//				wfDebug(__METHOD__ . ": Invalid destination width: width\n");
//
//				return false;
//			}
//			if (srcWidth <= 0) {
//				wfDebug(__METHOD__ . ": Invalid source width: srcWidth\n");
//
//				return false;
//			}
//
//			height = File::scaleHeight(srcWidth, srcHeight, width);
//			if (height == 0) {
//				# Force height to be at least 1 pixel
//				height = 1;
//			}
//
//			return true;
//		}
//
//		/**
//		* @param File image
//		* @param String script
//		* @param array params
//		* @return boolean|MediaTransformOutput
//		*/
//		function getScriptedTransform(image, script, params) {
//			if (!this.normaliseParams(image, params)) {
//				return false;
//			}
//			url = wfAppendQuery(script, this.getScriptParams(params));
//
//			if (image.mustRender() || params['width'] < image.getWidth()) {
//				return new ThumbnailImage(image, url, false, params);
//			}
//		}
//
//		function getImageSize(image, path) {
//			MediaWiki\suppressWarnings();
//			gis = getimagesize(path);
//			MediaWiki\restoreWarnings();
//
//			return gis;
//		}
//
//		/**
//		* Function that returns the number of pixels to be thumbnailed.
//		* Intended for animated GIFs to multiply by the number of frames.
//		*
//		* If the file doesn't support a notion of "area" return 0.
//		*
//		* @param File image
//		* @return int
//		*/
//		function getImageArea(image) {
//			return image.getWidth() * image.getHeight();
//		}
//
//		/**
//		* @param File file
//		* @return String
//		*/
//		function getShortDesc(file) {
//			global wgLang;
//			nbytes = htmlspecialchars(wgLang.formatSize(file.getSize()));
//			widthheight = wfMessage('widthheight')
//				.numParams(file.getWidth(), file.getHeight()).escaped();
//
//			return "widthheight (nbytes)";
//		}
//
//		/**
//		* @param File file
//		* @return String
//		*/
//		function getLongDesc(file) {
//			global wgLang;
//			pages = file.pageCount();
//			size = htmlspecialchars(wgLang.formatSize(file.getSize()));
//			if (pages === false || pages <= 1) {
//				msg = wfMessage('file-info-size').numParams(file.getWidth(),
//					file.getHeight()).params(size,
//						'<span class="mime-type">' . file.getMimeType() . '</span>').parse();
//			} else {
//				msg = wfMessage('file-info-size-pages').numParams(file.getWidth(),
//					file.getHeight()).params(size,
//						'<span class="mime-type">' . file.getMimeType() . '</span>').numParams(pages).parse();
//			}
//
//			return msg;
//		}
//
//		/**
//		* @param File file
//		* @return String
//		*/
//		function getDimensionsString(file) {
//			pages = file.pageCount();
//			if (pages > 1) {
//				return wfMessage('widthheightpage')
//					.numParams(file.getWidth(), file.getHeight(), pages).text();
//			} else {
//				return wfMessage('widthheight')
//					.numParams(file.getWidth(), file.getHeight()).text();
//			}
//		}
//
//		public function sanitizeParamsForBucketing(params) {
//			params = parent::sanitizeParamsForBucketing(params);
//
//			// We unset the height parameters in order to let normaliseParams recalculate them
//			// Otherwise there might be a height discrepancy
//			if (isset(params['height'])) {
//				unset(params['height']);
//			}
//
//			if (isset(params['physicalHeight'])) {
//				unset(params['physicalHeight']);
//			}
//
//			return params;
//		}
}
