/**
 * 
 */
package com.bitstunnel.jectools.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.bitstunnel.jectools.views.IssuesViewer;

/**
 * @author Mai
 *
 */
public class PerspectiveFactory implements IPerspectiveFactory {

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)
	 */
	@Override
	public void createInitialLayout(IPageLayout layout) {
		// TODO Auto-generated method stub
		String editorArea = layout.getEditorArea();
//		layout.addStandaloneViewPlaceholder(IssuesViewer.ID, IPageLayout.TOP, 0.75f, editorArea, true);
		layout.addStandaloneView(
				IssuesViewer.ID,
				true, 
				IPageLayout.TOP, 
				0.75f,
				editorArea );
//		
//		layout.addView(
//				IPageLayout.ID_OUTLINE, 
//				IPageLayout.BOTTOM,
//				0.25f,
//				editorArea);
		IFolderLayout bottom = layout.createFolder(
				"bottom",
				IPageLayout.BOTTOM,
				0.25f,
				editorArea);

		bottom.addView(IPageLayout.ID_PROBLEM_VIEW);
		bottom.addPlaceholder(IPageLayout.ID_PROBLEM_VIEW);

	}

}
